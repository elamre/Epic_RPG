package org.elmarsoft.image;

import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * <p/>
 * User: Elmar
 * Date: 2/28/13
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextBubble {
    private static final int TEXTWIDTH = 10;
    private static final int MAXWIDTH = 200;
    private ArrayList<TextLine> lines = new ArrayList<TextLine>();
    private int index = 0;

    public TextBubble(String message) {
        String textLine = "";
        int messageIndex = 0;
        for (int i = 0; i < message.length(); i++) {
            if (textLine.length() * TEXTWIDTH < MAXWIDTH) {
                textLine += message.charAt(i);
            } else {
                messageIndex++;
                lines.add(new TextLine(textLine, messageIndex));
                textLine = "";
            }
        }
    }

    public boolean readLine() {
        if (index < lines.size()) {
            index++;
            return false;
        }
        return true;
    }

    public void draw(Graphics graphics) {
        graphics.drawString(lines.get(index).getText(), 100, 100);
    }

    private class TextLine {
        String text;
        int index;

        TextLine(String text, int index) {
            this.text = text;
            this.index = index;
        }

        public String getText() {
            return text;
        }

        public int getIndex() {
            return index;
        }
    }
}
