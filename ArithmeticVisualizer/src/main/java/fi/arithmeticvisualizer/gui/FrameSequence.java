package fi.arithmeticvisualizer.gui;

import java.util.ArrayList;

public class FrameSequence {
    
    private final ArrayList<Frame> frames;
    private final int length;

    public FrameSequence(ArrayList<Frame> frames) {
        this.frames = frames;
        this.length = frames.size();
    }
    
    public Frame getFrame(int index) {
        return frames.get(index);
    }

    public int getLength() {
        return length;
    }
    
    
    
}
