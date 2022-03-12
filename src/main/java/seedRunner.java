import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.featureutils.loot.LootContext;
import kaptainwutax.featureutils.loot.MCLootTables;
import kaptainwutax.featureutils.loot.item.ItemStack;
import kaptainwutax.featureutils.structure.RuinedPortal;
import kaptainwutax.featureutils.structure.Village;
import kaptainwutax.mcutils.rand.ChunkRand;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.math.DistanceMetric;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class seedRunner {
    public static void main(String[] args) {
        long seed = -4172144997902289642L;
        System.out.println("Generating world: "+seed);

        ChunkRand chunkRand = new ChunkRand();

        BPos currentPosition;

        OverworldBiomeSource obs = new OverworldBiomeSource(MCVersion.v1_12_2,seed);
        BPos spawnPoint = obs.getSpawnPoint();
        System.out.println("Spawning in at "+spawnPoint);

        currentPosition = spawnPoint;

        Village village = new Village(MCVersion.v1_12_2);
        CPos village1 = village.getInRegion(seed,0,0,chunkRand);
        CPos village2 = village.getInRegion(seed,-1,0,chunkRand);
        CPos village3 = village.getInRegion(seed,-1,-1,chunkRand);
        CPos village4 = village.getInRegion(seed,0,-1,chunkRand);
        List<CPos> villagePositions = new ArrayList<>(Arrays.asList(village1,village2,village3,village4));

        double smallestDistance = Integer.MAX_VALUE;
        CPos closestVillage = new CPos(0,0);
        for(CPos villagePosition : villagePositions){
            double distance = spawnPoint.distanceTo(villagePosition.toBlockPos(), DistanceMetric.EUCLIDEAN);
            if(distance<smallestDistance){
                smallestDistance = distance;
                closestVillage = villagePosition;
            }
        }
        System.out.println("Heading to closest village at "+closestVillage.toBlockPos());
        currentPosition = closestVillage.toBlockPos();

        int regionX = closestVillage.getX()>=0 ? 0 : -1;
        int regionZ = closestVillage.getZ()>=0 ? 0 : -1;

        RuinedPortal ruinedPortal = new RuinedPortal(Dimension.OVERWORLD,MCVersion.v1_16_1);
        CPos closestRuinedPortal = ruinedPortal.getInRegion(seed,regionX,regionZ,chunkRand);

        System.out.println("Heading to ruined portal at "+closestRuinedPortal.toBlockPos());
        currentPosition = closestRuinedPortal.toBlockPos();

        chunkRand.setDecoratorSeed(seed, closestRuinedPortal.getX() * 16, closestRuinedPortal.getZ() * 16, 40005, MCVersion.v1_16_1);

        LootContext a1 = new LootContext(chunkRand.nextLong());
        List<ItemStack> ItemList = MCLootTables.RUINED_PORTAL_CHEST.generate(a1);
        for(ItemStack itemStack : ItemList){
            System.out.println("Looted "+itemStack.getCount()+" "+itemStack.getItem().getName());
        }

    }
}