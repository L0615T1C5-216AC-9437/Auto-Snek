package AS;

//-----imports-----//

import arc.Events;
import arc.struct.Array;
import mindustry.content.Blocks;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.plugin.Plugin;

import java.util.HashMap;


public class Main extends Plugin {
    public static Thread cycle;
    public static HashMap<String, Team> overflow = new HashMap<>();
    public Main()  {
        Events.on(EventType.BlockBuildEndEvent.class, event -> {
            if (!event.breaking) {
                if (event.tile.block().name.equals("inverted-sorter")) {
                    if (event.tile.getNearby(1, 0).block().name.equals("inverted-sorter") && event.tile.getNearby(2, 0).block().name.equals("inverted-sorter")) overflow.put(event.tile.x+","+event.tile.y, event.tile.getTeam());
                    if (event.tile.getNearby(-1, 0).block().name.equals("inverted-sorter") && event.tile.getNearby(-2, 0).block().name.equals("inverted-sorter")) overflow.put(event.tile.x+","+event.tile.y, event.tile.getTeam());
                    if (event.tile.getNearby(0, 1).block().name.equals("inverted-sorter") && event.tile.getNearby(0, 2).block().name.equals("inverted-sorter")) overflow.put(event.tile.x+","+event.tile.y, event.tile.getTeam());
                    if (event.tile.getNearby(0, -1).block().name.equals("inverted-sorter") && event.tile.getNearby(0, -2).block().name.equals("inverted-sorter")) overflow.put(event.tile.x+","+event.tile.y, event.tile.getTeam());
                }
            }
        });
        Events.on(EventType.WaveEvent.class, event -> {
            if (!cycle.isAlive()) {
                AS.cycle c = new cycle(Thread.currentThread());
                c.setDaemon(false);
                c.start();
            }
        });
        Events.on(EventType.ServerLoadEvent.class, event -> {
            AS.cycle c = new cycle(Thread.currentThread());
            c.setDaemon(false);
            c.start();
        });
    }
}
