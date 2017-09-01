package fi.arithmeticvisualizer.logic.frames;

import java.util.List;

/**
 * A FrameSequence contains the Frames that make up a single 
 * visualization animation.
 */
public class FrameSequence {
    
    private final List<Frame> frames;
    private final int length;

    /**
     * Constructs a FrameSequence from the provided Frames.
     * 
     * @param frames a list of the Frames to be included
     */
    public FrameSequence(List<Frame> frames) {
        this.frames = frames;
        this.length = frames.size();
    }
    
    /**
     * Returns the Frame with the specified index.
     * 
     * @param index the index of the Frame
     * @return the Frame with the specified index
     */
    public Frame getFrame(int index) {
        return frames.get(index);
    }

    /**
     * Returns the number of Frames contained.
     *
     * @return the number of Frames contained
     */
    public int getLength() {
        return length;
    }
    
}
