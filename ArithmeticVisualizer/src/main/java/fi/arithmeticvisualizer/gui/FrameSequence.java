package fi.arithmeticvisualizer.gui;

import java.util.List;

/**
 * A FrameSequence instance contains all the Frames that pertain to a single
 * animation. It also contains the FramePattern instance which, in conjunction
 * with the row and column indices contained in each Frame object, determines
 * which elements of each array are active and which elements of the result
 * array are shown in each frame of the animation.
 */
public class FrameSequence {
    
    private final List<Frame> frames;
    private final FramePattern pattern;
    private final int length;

    /**
     * Constructs a FrameSequence comprising the provided Frames and the
     * provided FramePattern.
     * 
     * @param frames a List of the Frames
     * @param pattern the FramePattern of the animation
     */
    public FrameSequence(List<Frame> frames, FramePattern pattern) {
        this.frames = frames;
        this.pattern = pattern;
        this.length = frames.size();
    }

    public List<Frame> getFrames() {
        return frames;
    }
    
    public Frame getFrame(int index) {
        return frames.get(index);
    }

    public FramePattern getPattern() {
        return pattern;
    }

    public int getLength() {
        return length;
    }
    
}
