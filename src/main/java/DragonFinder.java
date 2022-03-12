import kaptainwutax.biomeutils.biome.Biome;
import kaptainwutax.biomeutils.source.BiomeSource;
import kaptainwutax.biomeutils.source.OverworldBiomeSource;
import kaptainwutax.mcutils.block.Block;
import kaptainwutax.mcutils.state.Dimension;
import kaptainwutax.mcutils.util.pos.BPos;
import kaptainwutax.mcutils.util.pos.CPos;
import kaptainwutax.mcutils.version.MCVersion;
import kaptainwutax.terrainutils.TerrainGenerator;

public class DragonFinder {
    public static void main(String[] args) {
        long worldSeed = -4172144997902289642L;
        MCVersion version = MCVersion.v1_12_2;
        Dimension dimension = Dimension.OVERWORLD;
        OverworldBiomeSource ovw = new OverworldBiomeSource(version, worldSeed);
        long spiralX = 0, spiralZ = 0;
        long centerx = 0,centerz = 0;
        long steps;
        boolean isSkipping=false;
        long XMax = 30000000, YMax = 30000000;
        long t = Math.max(XMax, YMax);




        steps = 128;
        //isSkipping = (skip.getValue() != 0);
        //
    
        for (long i = 0; i < 1000000000; i++) {
                long x = spiralX + centerx; //add to move center
                long z = spiralZ + centerz; // ^
                String biomeAt = ovw.getBiome((int) x,0 , (int) z).getName();
                if(biomeAt == "desert" || biomeAt== "desert_hills"){
                    String biomeAt2 = ovw.getBiome((int) x ,0 , (int) z + 112).getName();
                    if (biomeAt2 == "river"){
//                        System.out.println("Match level 2 found!");
                        String biomeAt3 = ovw.getBiome((int) x-370,0 , (int)z + 112).getName();
                        if (biomeAt3=="deep_ocean" || biomeAt3 == "ocean"){
//                            System.out.println("MATCH LEVEL 3 FOUND!!!");
                            String biomeAt4 = ovw.getBiome((int) x-370,0 , (int) z + 225).getName();
                            if (biomeAt4=="badlands"||biomeAt4=="eroded_badlands"){
                                Biome biomeAt5 = ovw.getBiome((int)x+124,0,(int)z+241)
                                System.out.println("FULL MATCH FOUND!!!");
                                System.out.println(x + " " + z);
//                                return;
                            }
                        }

                    }

                }
                if (Math.abs(spiralX) <= Math.abs(spiralZ) && (spiralX != spiralZ || spiralX >= 0))
                    spiralX += ((spiralZ >= 0) ? steps : -steps);
                else
                    spiralZ += ((spiralX >= 0) ? -steps : steps);
            }
        }
    }


