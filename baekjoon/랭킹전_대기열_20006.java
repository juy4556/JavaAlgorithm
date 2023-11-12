package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 랭킹전_대기열_20006 {
    static class GameRoom {
        private final int roomLevel;
        private final List<Component> components;

        public GameRoom(int roomLevel, List<Component> components) {
            this.roomLevel = roomLevel;
            this.components = components;
        }

        public int getRoomLevel() {
            return roomLevel;
        }

        public List<Component> getComponents() {
            return components;
        }
    }

    static class Component {

        private final int level;
        private final String name;


        public Component(int level, String name) {
            this.level = level;
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<GameRoom> gameRooms = new ArrayList<>();
        for (int k = 0; k < p; k++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            Component component = new Component(l, n);
            boolean isUpdated = false;
            for (GameRoom gameRoom : gameRooms) {
                if (gameRoom.getComponents().size() >= m) continue;
                if (Math.abs(l - gameRoom.getRoomLevel()) <= 10) {
                    isUpdated = true;
                    gameRoom.getComponents().add(component);
                    break;
                }
            }
            if (!isUpdated) {
                List<Component> components = new ArrayList<>();
                components.add(component);
                GameRoom gameRoom = new GameRoom(l, components);
                gameRooms.add(gameRoom);
            }
        }

        for (GameRoom gameRoom : gameRooms) {
            gameRoom.getComponents().sort(Comparator.comparing(Component::getName));
            if (gameRoom.getComponents().size() < m) {
                sb.append("Waiting!").append('\n');
            } else {
                sb.append("Started!").append('\n');
            }
            for (Component component : gameRoom.getComponents()) {
                sb.append(component.getLevel()).append(' ').append(component.getName()).append('\n');
            }
        }
        System.out.print(sb);
    }
}
