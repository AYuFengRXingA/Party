package com.yufengandbabaozhou.partiesloader.UI;

import com.yufengandbabaozhou.partiesloader.GameInterfaces.IGameCreator;
import com.yufengandbabaozhou.partiesloader.PartiesLoader;
import com.yufengandbabaozhou.partiesloader.Server.ULPacket.LeaveGroupPacket;
import com.yufengandbabaozhou.partiesloader.Group.GroupManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;


public class SimpleScreen extends Screen{
    private String playerName =Minecraft.getInstance().player.getName().getString();
    GroupManager manager =GroupManager.getInstance();
    private GroupListWidget groupListWidget;


    int centerX= this.width / 2;
    int centerY= this.height / 2;

    public SimpleScreen(Component title) {
        super(title);

    }


    @Override
    protected void init() {
        super.init();
        int buttonWidth = 80;
        int buttonHeight = 20;



        List<IGameCreator> games = PartiesLoader.getAllGames();

        for (int i = 0; i < games.size(); i++) {
            IGameCreator game = games.get(i);
            int y = this.height/2 + i * 25;

            Button button = Button.builder(
                    Component.literal(game.getGameName()),
                    (btn) -> {

                        String owner = manager.getPlayerGroupId(playerName);
                        if (owner == null || !owner.equals(playerName)) {
                            Minecraft.getInstance().player.sendSystemMessage(
                                    Component.literal("§c你没有权限或者没有加入群组！")
                            );
                            return;
                        }
                        Screen screen = game.createUIScreen();
                        if (screen != null) {
                            Minecraft.getInstance().setScreen(screen);
                        }
                    }
            ).bounds(20, y, 100, 20).build();

            this.addRenderableWidget(button);
        }

        Button button1 = Button.builder(Component.literal("创建群组"), (btn) -> {
            Minecraft.getInstance().setScreen(new GroupSet(Component.literal("占位")));

        }).bounds(this.width/4-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(button1);

        Button button2 = Button.builder(Component.literal("加入群组"), (btn) -> {
            Minecraft.getInstance().setScreen(new ListSet(Component.literal("占位")));

        }).bounds(this.width/4*2-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(button2);

        Button button3 = Button.builder(Component.literal("离开群组"), (btn) -> {
            ListSet.groupListWidget.removeEntry(playerName);
            PartiesLoader.NETWORK.sendToServer(new LeaveGroupPacket());


        }).bounds(this.width/4*3-40, this.height/4-40, buttonWidth, buttonHeight).build();
        this.addRenderableWidget(button3);




    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {

        guiGraphics.fillGradient(0, 0, this.width, this.height, 0xC0101010, 0xD0101010);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }
}
