package sample;

public class DataCase {
    public String secretCopingInfo;
    public String caseName;
    public short xPosition;
    public short yPosition;

    public DataCase(String[] into, short xPosition, short yPosition) {
        caseName = into[0];
        secretCopingInfo = into[1];
        setXPosition(xPosition);
        setYPosition(yPosition);
    }

    public void setXPosition(short xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(short yPosition) {
        this.yPosition = yPosition;
    }
}