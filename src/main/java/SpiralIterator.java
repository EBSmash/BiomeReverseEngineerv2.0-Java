import kaptainwutax.mcutils.util.pos.CPos;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;
import java.lang.Math;
public class SpiralIterator {
    //no spiral yet
    @Contract(" -> new")
    public static @NotNull
    CPos scan() {
        int x = 0;
        int z = 0;
        int scanAreaX = 10;;
        int scanAreaZ = 10;;
        for(x = 0; x < scanAreaX; x++) {
            for (z = 0; z < scanAreaZ; z++) {
                System.out.println(String.valueOf(x));
                System.out.println(String.valueOf(z));
                return new CPos(x, z);
            }
        }
        return new CPos(0,0);
    }
}