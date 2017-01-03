package RebornStorage.multiblocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import reborncore.common.multiblock.IMultiblockPart;
import reborncore.common.multiblock.MultiblockControllerBase;
import reborncore.common.multiblock.rectangular.RectangularMultiblockControllerBase;
import reborncore.common.util.Inventory;

/**
 * Created by Mark on 03/01/2017.
 */
public class MultiBlockCrafter extends RectangularMultiblockControllerBase {

	//public MultiBlockInventory inventory = new MultiBlockInventory(5, "multicrafter", 1, this);

	public ItemStackHandler inv = new StackHandler(5);

	public MultiBlockCrafter(World world) {
		super(world);
	}

	@Override
	public void onAttachedPartWithMultiblockData(IMultiblockPart iMultiblockPart, NBTTagCompound nbtTagCompound) {

	}

	@Override
	protected void onBlockAdded(IMultiblockPart iMultiblockPart) {

	}

	@Override
	protected void onBlockRemoved(IMultiblockPart iMultiblockPart) {

	}

	@Override
	protected void onMachineAssembled() {
		inv.setSize(getNumConnectedBlocks() * 11);
		System.out.println(getNumConnectedBlocks() * 11 + " slots");
	}

	@Override
	protected void onMachineRestored() {

	}

	@Override
	protected void onMachinePaused() {

	}

	@Override
	protected void onMachineDisassembled() {
		System.out.println("Invalid");
	}

	@Override
	protected int getMinimumNumberOfBlocksForAssembledMachine() {
		return 1;
	}

	@Override
	protected int getMaximumXSize() {
		return 256;
	}

	@Override
	protected int getMaximumZSize() {
		return 256;
	}

	@Override
	protected int getMaximumYSize() {
		return 256;
	}

	@Override
	protected int getMinimumXSize() {
		return 2;
	}

	@Override
	protected int getMinimumYSize() {
		return 2;
	}

	@Override
	protected int getMinimumZSize() {
		return 2;
	}

	@Override
	protected void onAssimilate(MultiblockControllerBase multiblockControllerBase) {

	}

	@Override
	protected void onAssimilated(MultiblockControllerBase multiblockControllerBase) {

	}

	@Override
	protected boolean updateServer() {
		return true;
	}

	@Override
	protected void updateClient() {

	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
        nbtTagCompound.merge(inv.serializeNBT());
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
        inv.deserializeNBT(nbtTagCompound);
	}

	@Override
	public void formatDescriptionPacket(NBTTagCompound nbtTagCompound) {
		writeToNBT(nbtTagCompound);
	}

	@Override
	public void decodeDescriptionPacket(NBTTagCompound nbtTagCompound) {
		readFromNBT(nbtTagCompound);
	}

    public ItemStackHandler getInv()
    {
        return inv;
    }

    class StackHandler extends ItemStackHandler
	{
		StackHandler(int size)
		{
			super(size);
		}
	}
}