package behavioural.visitor;

import java.util.ArrayList;
import java.util.List;

public class Exercise {
    public static void main(String[] args) {
        WavFile wavFile = WavFile.read("myfile.wav");
        wavFile.applyFilter(new NoiseReductionFilter());
        wavFile.applyFilter(new ReverbFilter());
        wavFile.applyFilter(new NormalizeFilter());
    }
}

interface AudioFilter {
    void apply(FormatSegment formatSegment);
    void apply(FactSegment factSegment);
}

class NoiseReductionFilter implements AudioFilter {
    @Override
    public void apply(FormatSegment formatSegment) {
        System.out.println("Noise reduction on format segment");
    }
    @Override
    public void apply(FactSegment factSegment) {
        System.out.println("Noise reduction on fact segment");
    }
}

class NormalizeFilter implements AudioFilter {
    @Override
    public void apply(FormatSegment formatSegment) {
        System.out.println("Normalize on format segment");
    }
    @Override
    public void apply(FactSegment factSegment) {
        System.out.println("Normalize on fact segment");
    }
}

class ReverbFilter implements AudioFilter {
    @Override
    public void apply(FormatSegment formatSegment) {
        System.out.println("Reverb filter on format segment");
    }
    @Override
    public void apply(FactSegment factSegment) {
        System.out.println("Reverb filter on fact segment");
    }
}

abstract class Segment {
    public abstract void applyFilter(AudioFilter filter);
}

class FactSegment extends Segment {
    @Override
    public void applyFilter(AudioFilter filter) {
        filter.apply(this);
    }
}

class FormatSegment extends Segment {
    @Override
    public void applyFilter(AudioFilter filter) {
        filter.apply(this);
    }
}


class WavFile {
    private List<Segment> segments = new ArrayList<>();
    public static WavFile read(String fileName) {
        WavFile wavFile = new WavFile();
        wavFile.segments.add(new FormatSegment());
        wavFile.segments.add(new FactSegment());
        wavFile.segments.add(new FactSegment());
        wavFile.segments.add(new FactSegment());
        return wavFile;
    }
    public void applyFilter(AudioFilter filter) {
        for (var segment : segments)
            segment.applyFilter(filter);
    }
}