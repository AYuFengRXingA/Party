package com.yufengandbabaozhou.partiesloader.UI;

import com.yufengandbabaozhou.partiesloader.group.Group;
import com.yufengandbabaozhou.partiesloader.group.GroupManager;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class ListSet extends Screen {
    public static GroupListWidget groupListWidget;

    int buttonWidth = 80;
    int buttonHeight = 20;
    @Override
    public void init(){
        super.init();

        groupListWidget = new GroupListWidget(0,100,this.width,this.height);

        List<Group> allGroups = GroupManager.getInstance().getAllGroups();
        for (Group group : allGroups) {
            groupListWidget.addEntry(group.getGroupName() + " (ID: " + group.getGroupId() + ")");
        }
        this.addRenderableWidget(groupListWidget);

    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }



    public ListSet(Component title) {
        super(title);
    }
    public void add(String playerName){
        groupListWidget.addEntry(playerName);
    }
    public GroupListWidget getGroupList(){
        return groupListWidget;
    }
}
