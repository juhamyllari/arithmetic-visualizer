package fi.arithmeticvisualizer.gui;

import java.util.List;

public class FrameSequence {
    
    private final List<Frame> frames;
    private final OperationPattern pattern;
    private final int length;

    public FrameSequence(List<Frame> frames, OperationPattern pattern) {
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

    public OperationPattern getPattern() {
        return pattern;
    }

    public int getLength() {
        return length;
    }
    
}
