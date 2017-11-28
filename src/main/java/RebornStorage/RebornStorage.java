package RebornStorage;

import RebornStorage.client.GuiHandler;
import RebornStorage.init.ModBlocks;
import RebornStorage.init.ModItems;
import RebornStorage.init.ModRecipes;
import RebornStorage.lib.CommandBuildMultiBlock;
import RebornStorage.lib.ModInfo;
import RebornStorage.packet.Packets;
import RebornStorage.proxys.CommonProxy;
import RebornStorage.tiles.CraftingNode;
import com.raoulvdberge.refinedstorage.apiimpl.network.node.NetworkNode;
import com.raoulvdberge.refinedstorage.util.StackUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * Created by Gigabit101 on 03/01/2017.
 */
@Mod(name = ModInfo.MOD_NAME, modid = ModInfo.MOD_ID, version = ModInfo.MOD_VERSION, dependencies = ModInfo.MOD_DEPENDENCUIES)
public class RebornStorage {
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY_LOC, serverSide = ModInfo.COMMON_PROXY_LOC)
	public static CommonProxy proxy;

	@Mod.Instance
	public static RebornStorage INSTANCE;

	public static final String MULTI_BLOCK_ID = ModInfo.MOD_ID  + ":multiblockcrafter";

	@Mod.EventHandler
	public void preinit(FMLPreInitializationEvent event) {
		ModItems.init();
		ModBlocks.init();
		ModRecipes.init();
		proxy.registerRenders();
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
		MinecraftForge.EVENT_BUS.register(Packets.class);

		RSHelper.API.getNetworkNodeRegistry().add(MULTI_BLOCK_ID, (tag, world, pos) -> {
			CraftingNode node = new CraftingNode(world, pos);
			StackUtils.readItems(node.patterns, 0, tag);
			return node;
		});

	}

	@Mod.EventHandler
	public void serverStarted(FMLServerStartingEvent event){
		event.registerServerCommand(new CommandBuildMultiBlock());
	}
}
