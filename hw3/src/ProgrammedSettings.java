public class ProgrammedSettings {
    int temperature;

    public ProgrammedSettings() {
        temperature = 76;
    }

    public ProgrammedSettings(int t) {
        temperature = t;
    }

    public int getSetting(Period p, DayType day) {
        return temperature;
    }
}