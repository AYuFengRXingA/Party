package com.yufengandbabaozhou.partiesloader.UI;

import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.partiesloader.Server.ULPacket.CreateGroupPacket;
import com.yufengandbabaozhou.partiesloader.group.GroupManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class GroupSet extends Screen {
    GroupManager manager = GroupManager.getInstance();

GroupListWidget.Entry entry ;

     private String playerName =Minecraft.getInstance().player.getName().getString();
    public GroupSet(Component title) {
        super(title);
    }

    public void init(){
        super.init();

        int buttonWidth = 80;
        int buttonHeight = 20;

        int boxWidth = 200;
        int boxHeight = 20;


        EditBox maxplayer = new EditBox(minecraft.getInstance().font,
                this.width/2,this.height/4,
                boxWidth,boxHeight,
                Component.literal("ID"));
        maxplayer.setHint(Component.literal("请输入群组ID"));
        this.addRenderableWidget(maxplayer);


        Button buttonSET = Button.builder(Component.literal("创建群组"), (btn) -> {
            String ID = maxplayer.getValue();
            if(ID.isEmpty()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("§c请输入ID"));
                return;
            } else if (manager.isInGroup(playerName)) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("§a你已经加入群组"));
                return;
            }

            PartiesLoader.NETWORK.sendToServer(new CreateGroupPacket(playerName));


        }).bounds(this.width/4-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(buttonSET);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
