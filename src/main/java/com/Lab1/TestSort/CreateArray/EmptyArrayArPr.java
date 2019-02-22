package com.Lab1.TestSort.CreateArray;

/**
 * Creator of empty arrays with step
 */
public class EmptyArrayArPr extends EmptyArray {

    private int step;
    private int start;

    public EmptyArrayArPr(int start, int step) {
        this.start = start;
        this.step = step;
    }

    public EmptyArrayArPr() {
        this.start = 1;
        this.step = 1;
    }

    @Override
    public int[] createArray() {
        return new int[getSteepAndTakeStep()];
    }

    public int nextStep() {
        int i = getStart() + getStep();
        setStart((i < 0) ? Integer.MAX_VALUE : i);
        return getStart();
    }

    public int getStep() {
        return step;
    }

    public int getSteepAndTakeStep() {
        int i = getStart();
        nextStep();
        return i;
    }

    public void setStep(int step) {
        if (step < 1) {
            this.step = 1;
        } else {
            this.step = step;
        }
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        if (start < 1) {
            this.start = 1;
        } else {
            this.start = start;
        }
    }
}
