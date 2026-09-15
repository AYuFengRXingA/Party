package com.yufengandbabaozhou.werewolf.Server.DPacket;

import com.yufengandbabaozhou.werewolf.UIConfig;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class NumberPacket {
    int wolf;
    int witch;
    int hunter;
    public NumberPacket(int wolf, int witch, int hunter) {
        this.wolf = wolf;
        this.witch = witch;
        this.hunter = hunter;
    }

    public static void encode(NumberPacket msg, FriendlyByteBuf buf){
        buf.writeInt(msg.wolf);
        buf.writeInt(msg.witch);
        buf.writeInt(msg.hunter);
    }
    public static NumberPacket decode(FriendlyByteBuf buf){
        return new NumberPacket(buf.readInt(),buf.readInt(),buf.readInt());
    }

    public static void handle(NumberPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {

        UIConfig.setNumber(msg.wolf,msg.witch,msg.hunter);
        });
        ctx.get().setPacketHandled(true);
    }

}
