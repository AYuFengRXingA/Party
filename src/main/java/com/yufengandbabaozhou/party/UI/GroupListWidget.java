package com.yufengandbabaozhou.party.UI;

import com.yufengandbabaozhou.party.Party;
import com.yufengandbabaozhou.party.PlayerTag;
import com.yufengandbabaozhou.party.Server.JoinGroupPacket;
import com.yufengandbabaozhou.party.group.GroupManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.network.chat.Component;

import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

public class GroupListWidget extends ObjectSelectionList<GroupListWidget.Entry> {



    public GroupListWidget(int x, int y, int width, int height) {
        super(Minecraft.getInstance(), width, height, y, y + height, 25);
        this.setLeftPos(x);
    }

    public void addEntry(String text) {
        this.addEntry(new Entry(text));

    }

    public void clearAll() {
        this.clearEntries();
    }

    @Override
    protected void renderBackground(GuiGraphics guiGraphics) {
        int x = this.x0;
        int y = this.y0;
        int w = this.width;
        int h = this.height;
        guiGraphics.fill(x, y, x + w, y + h, 0xFF222244);

    }


    public static class Entry extends ObjectSelectionList.Entry<Entry> {
        public final String text;


        public Entry(String text) {
            this.text = text;

        }



        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            if (button == 0) {// 左键

                Party.NETWORK.sendToServer(new JoinGroupPacket(text));
                return true;
            }
            return false;
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int y, int x, int entryWidth, int entryHeight,
                           int mouseX, int mouseY, boolean hovered, float partialTick) {

            int bgColor = (index % 2 == 0) ? 0x33FFFFFF : 0x11FFFFFF;
            guiGraphics.fill(x, y, x + entryWidth, y + entryHeight, bgColor);

            guiGraphics.drawString(
                    Minecraft.getInstance().font,
                    text,
                    x + 10,
                    y + 4,
                    0xFFFFFF
            );

            if (hovered) {
                guiGraphics.fill(x, y, x + entryWidth, y + entryHeight, 0x44FFAA00);
            }
        }

        @Override
        public Component getNarration() {
            return Component.literal(text);
        }
    }

}