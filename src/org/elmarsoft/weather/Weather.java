package org.elmarsoft.weather;

import org.elmarsoft.main.Game;
import org.elmarsoft.map.MapOrganizer;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 2/27/13
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class Weather {
    public static final WeatherSettings CLEARWEATHER = new WeatherSettings(false, 0, 0.843f, 0.843f, 0.843f, 0.1f, 0.00f, 0.4f, 3, 0.01f, 0.05f, 0);
    public static final WeatherSettings CLOUDY = new WeatherSettings(false, 0, 0.743f, 0.743f, 0.743f, 0.4f, 0.00f, 0.6f, 5, 0.05f, 0.08f, 0.4f);
    public static final WeatherSettings RAINY = new WeatherSettings(true, 150, 0.743f, 0.743f, 0.743f, 0.5f, 0.10f, 0.7f, 8, 0.08f, 0.10f, 0.5f);
    public static final WeatherSettings STORM = new WeatherSettings(true, 250, 0.400f, 0.400f, 0.400f, 0.8f, 0.15f, 0.8f, 12, 0.10f, 0.15f, 0.6f);
    private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
    private int randomWeather = 7;
    private WeatherSettings weather;
    private boolean flashing = false;
    private float flashAlpha = 1;
    private float flashDelta = 1;
    private Rain rain = new Rain();
    private float targetAlphaOverlay = 0;
    private float currentAlphaOverlay = 0;
    private float weatherChangeTimer = 0;

    public Weather() {
        setWeather(CLEARWEATHER);
    }

    public void setWeather(WeatherSettings weather) {
        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).changeWeather(weather);
        }
        if (weather.rain)
            rain.start();
        else
            rain.stop();
        rain.setMax(weather.getMaxRain());
        this.targetAlphaOverlay = weather.getAlphaOverlay();
        this.weather = weather;
    }

    public void update(float deltaT) {
        weatherChangeTimer += deltaT;
        if (weatherChangeTimer > 5000) {
            switch (new Random().nextInt(randomWeather)) {
                case 1:
                    System.out.println("Cloudy");
                    setWeather(CLOUDY);
                    if (randomWeather > 3) {
                        randomWeather--;
                    }
                    break;
                case 2:
                    System.out.println("Rainy");
                    setWeather(RAINY);
                    randomWeather = 8;
                    weatherChangeTimer -= 2000;
                    break;
                case 3:
                    System.out.println("Storm!");
                    setWeather(STORM);
                    weatherChangeTimer -= 3000;
                    randomWeather = 8;
                    break;
                default:
                    System.out.println("Sunny");
                    setWeather(CLEARWEATHER);
                    if (randomWeather > 3) {
                        randomWeather--;
                    }
                    break;
            }
            weatherChangeTimer -= 5000;
        }
        if (currentAlphaOverlay < targetAlphaOverlay) {
            currentAlphaOverlay += 0.001 * deltaT;
        }
        if (currentAlphaOverlay > targetAlphaOverlay) {
            currentAlphaOverlay -= 0.001 * deltaT;
        }
        if (clouds.size() < weather.cloudAmount) {
            clouds.add(new Cloud(new Random().nextInt(MapOrganizer.getMapOrganizer().getWidth() * Game.TILESIZE), new Random().nextInt(MapOrganizer.getMapOrganizer().getHeight() * Game.TILESIZE)));
        }
        ArrayList<Cloud> removalList = new ArrayList<Cloud>();
        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).update(deltaT, null);
            if (!clouds.get(i).isAlive()) {
                removalList.add(clouds.get(i));
            }
        }
        for (int i = 0; i < removalList.size(); i++) {
            clouds.remove(removalList.get(i));
        }
        //if (weather.equals(Weather.STORM) || weather.equals(Weather.RAINY))
        rain.update(deltaT);
        if (weather.equals(Weather.STORM)) {
            Random random = new Random();
            if (flashing == false) {
                if (random.nextInt(600) == 42) {
                    flashAlpha = random.nextFloat();
                    flashing = true;
                }
            }
        }
        if (flashing) {
            if (flashAlpha > 0.4) {
                flashAlpha -= deltaT / 1000;
            } else if (flashAlpha > 0) {
                if (flashDelta > 0.0001f)
                    flashDelta -= deltaT / 40000;
                flashAlpha -= flashDelta / 1000;
                if (flashAlpha < 0.25)
                    flashAlpha -= flashDelta / 1000;
            } else {
                flashing = false;
                flashDelta = 1;
            }
        }
    }

    public void draw(Graphics g, Rectangle screenPos) {
        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).draw(g, screenPos);
        }
        if (weather.equals(STORM)) {
            g.setColor(new Color(currentAlphaOverlay - currentAlphaOverlay / 3, currentAlphaOverlay - currentAlphaOverlay / 3, currentAlphaOverlay - currentAlphaOverlay / 3, currentAlphaOverlay));
            g.fill(screenPos);
        } else if (weather.equals(RAINY)) {
            g.setColor(new Color(currentAlphaOverlay, currentAlphaOverlay, currentAlphaOverlay, currentAlphaOverlay));
            g.fill(screenPos);
        } else {
            g.setColor(new Color(currentAlphaOverlay, currentAlphaOverlay, currentAlphaOverlay, currentAlphaOverlay));
            g.fill(screenPos);
        }
        if (flashing) {
            g.setColor(new Color(1, 1, 1, flashAlpha));
            g.fill(screenPos);
        }
        rain.draw(g, screenPos);


    }

    public static class WeatherSettings {
        private Color cloudColor = null;
        private Color shadowColor = null;
        private float speed = 0;
        private float speedMax, speedMin;
        private float alphaOverlay = 0;
        private int cloudAmount;
        private boolean rain = false;
        private int maxRain = 0;

        public WeatherSettings(boolean rain, int maxRain, float sR, float sG, float sB, float sA, float cB, float cA, int cloudAmount, float speedMin, float speedMax, float alphaOverlay) {
            cloudColor = new Color(1 - cB, 1 - cB, 1, cA);
            shadowColor = new Color(sR, sG, sB, sA);
            this.speedMax = speedMax;
            this.speedMin = speedMin;
            this.cloudAmount = cloudAmount;
            this.alphaOverlay = alphaOverlay;
            this.maxRain = maxRain;
            this.rain = rain;
        }

        public boolean getRain() {
            return rain;
        }

        public int getMaxRain() {
            return maxRain;
        }

        public Color getCloudColor() {
            return cloudColor;
        }

        public int getCloudAmount() {
            return cloudAmount;
        }

        public float getAlphaOverlay() {
            return alphaOverlay;
        }

        public Color getShadowColor() {
            return shadowColor;
        }

        public float getSpeed() {
            Random random = new Random();
            speed = random.nextFloat() * (speedMax - speedMin);
            speed += speedMin;
            return speed;
        }
    }
}
