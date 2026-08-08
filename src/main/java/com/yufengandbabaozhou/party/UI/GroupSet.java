package com.yufengandbabaozhou.party.UI;

import com.yufengandbabaozhou.party.Party;
import com.yufengandbabaozhou.party.Server.CreateGroupPacket;
import com.yufengandbabaozhou.party.group.Group;
import com.yufengandbabaozhou.party.group.GroupManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

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
        String ID = maxplayer.getValue();

        Button buttonSET = Button.builder(Component.literal("创建群组"), (btn) -> {
            if(ID == null) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("输入ID"));
                return;
            } else if (manager.isInGroup(playerName)) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("你已经加入群组"));
                return;
            }
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("测试"+ID));
            Party.NETWORK.sendToServer(new CreateGroupPacket(ID));
            /*Group group = manager.createGroup(playerName,ID);
            System.out.println("已创建群组："+ID);
            ListSet.groupListWidget.addEntry(ID+"的群组");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("已创建群组："+ID));*/

        }).bounds(this.width/4-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(buttonSET);
    }

    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
