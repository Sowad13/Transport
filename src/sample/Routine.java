package sample;

public class Routine {

        String plateNum;
        int travelTime;
        String start;
        String dest;
        String interval;

        public Routine() {
        }

        public Routine(String plateNum, int travelTime, String start, String dest, String interval) {
            this.plateNum = plateNum;
            this.travelTime = travelTime;
            this.start = start;
            this.dest = dest;
            this.interval = interval;
        }

        public String getPlateNum() {
            return plateNum;
        }

        public void setPlateNum(String plateNum) {
            this.plateNum = plateNum;
        }

        public int getTravelTime() {
            return travelTime;
        }

        public void setTravelTime(int travelTime) {
            this.travelTime = travelTime;
        }

        public String getStart() {
            return start;
        }

        public void setStart(String start) {
            this.start = start;
        }

        public String getDest() {
            return dest;
        }

        public void setDest(String dest) {
            this.dest = dest;
        }

        public String getInterval() {
            return interval;
        }

        public void setInterval(String interval) {
            this.interval = interval;
        }
    }



