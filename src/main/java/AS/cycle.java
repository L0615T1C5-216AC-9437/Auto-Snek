package AS;

import arc.util.Log;
import arc.util.Strings;
import arc.util.Time;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.gen.Call;
import mindustry.net.Administration;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static AS.Main.*;


public class cycle extends Thread {
    private Thread MainT;
    private HashMap<Integer, Integer> spawnTimer = new HashMap<Integer, Integer>();

    public cycle(Thread main) {
        MainT = main;
    }

    public void run() {
        Log.info("Auto-Snek cycle started - Waiting 15 Seconds");
        Main.cycle = Thread.currentThread();
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (Exception ignored) {
        }
        Log.info("Auto-Snek cycle running");
        //Var
        long nexSecond = Time.millis()+1000;
        //
        while (MainT.isAlive()) {
            try {
                try {
                    TimeUnit.MILLISECONDS.sleep(nexSecond - Time.millis());
                } catch (Exception ignored) {
                }
                nexSecond = Time.millis()+50;

                if (!overflow.isEmpty()) {
                    overflow.forEach((k,v) -> {
                        String[] cords = k.split(",");
                        Call.onConstructFinish(Vars.world.tile(Strings.parseInt(cords[0]), Strings.parseInt(cords[1])), Blocks.overflowGate, 0, (byte)0, v, false);
                    });
                    overflow.clear();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
