package concurrent.blockingqueue;

public class Page {
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isPoisonPill(){
        return this instanceof PoisonPill;
    }
}