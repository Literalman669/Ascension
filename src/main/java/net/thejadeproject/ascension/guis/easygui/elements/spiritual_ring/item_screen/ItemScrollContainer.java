package net.thejadeproject.ascension.guis.easygui.elements.spiritual_ring.item_screen;

import net.lucent.easygui.elements.containers.EmptyContainer;
import net.lucent.easygui.interfaces.ContainerRenderable;
import net.lucent.easygui.interfaces.IEasyGuiScreen;
import net.lucent.easygui.interfaces.events.MouseScrollListener;
import net.minecraft.client.gui.GuiGraphics;
import net.thejadeproject.ascension.guis.easygui.screens.SpatialRingItemContainerScreen;
import net.thejadeproject.ascension.guis.menu.SpatialRingItemContainerMenu;

public class ItemScrollContainer extends EmptyContainer implements MouseScrollListener {
    private int slotOffset;
    public ItemScrollContainer(IEasyGuiScreen screen,int x, int y, int height){
        super(screen,x,y,18*9,height);

    }
    public int getTotalOverflowRows(){
        SpatialRingItemContainerMenu menu = ((SpatialRingItemContainerScreen) getScreen()).getMenu();
        return Math.max(menu.getTotalRows()-menu.getVisibleRows(),0);
    }
    public int getVisibleRows(){
        SpatialRingItemContainerMenu menu = ((SpatialRingItemContainerScreen) getScreen()).getMenu();
        return Math.min(menu.getTotalRows(),menu.getVisibleRows());
    }

    public int getSlotOffset() {
        return slotOffset;
    }

    public int getTotalRows() {
        SpatialRingItemContainerMenu menu = ((SpatialRingItemContainerScreen) getScreen()).getMenu();
        return menu.getTotalRows();
    }

    public boolean hasOverflow() {
        return getTotalOverflowRows() > 0;
    }

    public float getScrollProgress() {
        int maxOffset = getTotalOverflowRows();
        if (maxOffset <= 0) {
            return 0.0F;
        }
        return slotOffset / (float) maxOffset;
    }

    public float getVisibleFraction() {
        int totalRows = getTotalRows();
        if (totalRows <= 0) {
            return 1.0F;
        }
        return Math.min(getVisibleRows() / (float) totalRows, 1.0F);
    }

    @Override
    public void onMouseScroll(double mouseX, double mouseY, double scrollX, double scrollY) {
        //negative is up + is down
        int change = (int) Math.signum(scrollY) * -1;
        if (change == 0) {
            return;
        }
        int oldOffset = slotOffset;
        slotOffset = Math.clamp(slotOffset + change, 0, getTotalOverflowRows());
        if(oldOffset != slotOffset){
            updateYPos(change);
            updateChildVisibility();
        }
    }

    @Override
    public void renderSelf(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderSelf(guiGraphics, mouseX, mouseY, partialTick);

    }

    public void updateChildVisibility(){
        int visibleRowsHeight = getVisibleRows() * 18;
        for (ContainerRenderable renderable : getChildren()) {
            renderable.setVisible(!(renderable.getY() + renderable.getHeight() < 0 || renderable.getY() >= visibleRowsHeight));
        }
    }
    public void updateYPos(int direction){
        for(ContainerRenderable renderable : getChildren()){
            renderable.setY(renderable.getY()-direction*18);
        }
    }
    @Override
    public void renderChildren(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        guiGraphics.pose().pushPose();
        updateChildVisibility();
        super.renderChildren(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.pose().popPose();
    }
}
