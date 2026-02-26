package net.thejadeproject.ascension.guis.easygui.elements.skill_menu_view.passive_skill_container;

import net.lucent.easygui.elements.containers.EmptyContainer;
import net.lucent.easygui.interfaces.IEasyGuiScreen;
import net.lucent.easygui.util.textures.TextureDataSubSection;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.thejadeproject.ascension.AscensionCraft;

public class PassiveSkillsContainer extends EmptyContainer {
    private final TextureDataSubSection background =  new TextureDataSubSection(
            ResourceLocation.fromNamespaceAndPath(AscensionCraft.MOD_ID,"textures/gui/screen/skill_stuff/skill_menu.png"),
            320,256,
            198,0,320,208
    );
    private final PassiveSkillScrollContainer passiveSkillScrollContainer;
    public PassiveSkillsContainer(IEasyGuiScreen screen){
        super(screen,0,0,0,0);
        setWidth(122);
        setHeight(208);
        setX(198);
        passiveSkillScrollContainer = new PassiveSkillScrollContainer(screen,11,15,8);
        addChild(passiveSkillScrollContainer);
    }



    @Override
    public void renderSelf(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderSelf(guiGraphics, mouseX, mouseY, partialTick);
        background.renderTexture(guiGraphics);
        renderScrollbar(guiGraphics);
    }

    private void renderScrollbar(GuiGraphics guiGraphics) {
        if (!passiveSkillScrollContainer.hasOverflow()) {
            return;
        }
        int trackX = 114;
        int trackY = 15;
        int trackHeight = 184;
        int trackWidth = 4;
        guiGraphics.fill(trackX, trackY, trackX + trackWidth, trackY + trackHeight, 0xFF1B2230);

        int thumbHeight = Math.max(14, Math.round(trackHeight * passiveSkillScrollContainer.getVisibleFraction()));
        int thumbY = trackY + Math.round((trackHeight - thumbHeight) * passiveSkillScrollContainer.getScrollProgress());
        guiGraphics.fill(trackX + 1, thumbY, trackX + trackWidth - 1, thumbY + thumbHeight, 0xFF6EA4FF);
    }
}
