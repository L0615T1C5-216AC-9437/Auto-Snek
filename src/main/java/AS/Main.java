package AS;

//-----imports-----//

import arc.Events;
import mindustry.content.Blocks;
import mindustry.game.EventType;
import mindustry.gen.Call;
import mindustry.plugin.Plugin;


public class Main extends Plugin {
    public Main()  {
        Events.on(EventType.BlockBuildEndEvent.class, event -> {
            if (!event.breaking) {
                if (event.tile.block().name.equals("inverted-sorter")) {
                    if (event.tile.getNearby(1, 0).block().name.equals("inverted-sorter") && event.tile.getNearby(2, 0).block().name.equals("inverted-sorter")) Call.onConstructFinish(event.tile, Blocks.overflowGate, 0, (byte)0, event.tile.getTeam(), false);
                    if (event.tile.getNearby(-1, 0).block().name.equals("inverted-sorter") && event.tile.getNearby(-2, 0).block().name.equals("inverted-sorter")) Call.onConstructFinish(event.tile, Blocks.overflowGate, 0, (byte)0, event.tile.getTeam(), false);
                    if (event.tile.getNearby(0, 1).block().name.equals("inverted-sorter") && event.tile.getNearby(0, 2).block().name.equals("inverted-sorter")) Call.onConstructFinish(event.tile, Blocks.overflowGate, 0, (byte)0, event.tile.getTeam(), false);
                    if (event.tile.getNearby(0, -1).block().name.equals("inverted-sorter") && event.tile.getNearby(0, -2).block().name.equals("inverted-sorter")) Call.onConstructFinish(event.tile, Blocks.overflowGate, 0, (byte)0, event.tile.getTeam(), false);
                }
            }
        });
    }
}
