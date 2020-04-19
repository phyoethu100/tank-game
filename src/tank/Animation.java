package tank;

import java.awt.*;

public class Animation {

    private boolean loop;
    protected boolean stop;
    private int frameCount, frameDelay, currentFrame, x, y, duration;
    private Sprite element;

    public Animation(Sprite element, int x, int y, int frameDelay, boolean loop) {
        this.element = element;
        this.x = x;
        this.y = y;
        this.loop = loop;
        this.stop = false;
        this.duration = 0;
        this.frameCount = 0;
        this.frameDelay = frameDelay;
        this.currentFrame = 0;
    }

    public boolean stop() {
        return this.stop;
    }

    public int totalTime() {
      return this.frameDelay * element.count();
    }

    public void draw(Graphics graphics) {
        if(!stop) {
            duration++;
            frameCount++;

        if (frameCount > frameDelay) {
            frameCount = 0;
            stop = (duration > this.totalTime()) && ! loop;
            currentFrame = (currentFrame + 1) % element.count();
        }
            graphics.drawImage(element.getImage(currentFrame), x, y,null);
       }
    }

}

