package org.example;

public class Range {
    private Long sourceRange;
    private Long destinationRange;
    private Long rangeLength;

    public Range(Long sourceRange, Long destinationRange, Long rangeLength){
        this.sourceRange = sourceRange;
        this.destinationRange = destinationRange;
        this.rangeLength = rangeLength;
    }


    public Long getSourceRange() {
        return sourceRange;
    }

    public Long getRangeLength() {
        return rangeLength;
    }

    public Long getDestinationRange() {
        return destinationRange;
    }

    public boolean inRange(long num){
        return num <= sourceRange + rangeLength -1 && num >= sourceRange;
    }
    public Long result(Long seed){
        if(!inRange(seed)){
            return null;
        }else{
            return getDestinationRange() + (seed-getSourceRange());
        }
    }

}
