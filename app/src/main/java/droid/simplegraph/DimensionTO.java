package droid.simplegraph;


public class DimensionTO{

    private String desc;

    private int dimsId;

    private String dimsName;

    private float score;

    public int getDimsId() {
        return dimsId;
    }

    public void setDimsId(int dimsId) {
        this.dimsId = dimsId;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDimsName() {
        return dimsName;
    }

    public void setDimsName(String dimsName) {
        this.dimsName = dimsName;
    }

    public DimensionTO(String dimsName, float score) {
        this.dimsName = dimsName;
        this.score = score;
    }
}
