package org.elmarsoft.story;

/**
 * Created with IntelliJ IDEA.
 * User: Elmar
 * Date: 3/5/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class Conversation {
    private static Conversation conversation;
    private static final String XML_LOCATION = "";

    public static Conversation getConversation() {
        if (conversation == null)
            conversation = new Conversation();
        return conversation;
    }

    public void changeText(String name, String text) {

    }

    public void setRead(boolean read, String name) {

    }

    public void setRead(int index, boolean read, String name) {

    }

    public boolean getRead(int index) {
        return false;
    }

    public boolean getRead() {
        return false;
    }

    public String getText(String name, int index) {
        return "This is just an example text not yet read from an XML file, but that will come someday very soon!...";
    }

    public String getText(String name) {
        return "";
    }
}
