package com.yufengandbabaozhou.werewolf.Server.UPacket;

import com.yufengandbabaozhou.werewolf.Server.WereWolfManager;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AddWolfPacket {

    public static void encode(AddWolfPacket msg, FriendlyByteBuf buf) {
    }

    public static AddWolfPacket decode(FriendlyByteBuf buf) {
        return new AddWolfPacket();
    }

    public static void handle(AddWolfPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            System.out.println(" 服务端收到 AddWolfPacket");
            WereWolfManager.getInstance().addWolf();
            System.out.println(" 服务端 Wolf = " + WereWolfManager.getInstance().getwolf());
        });
        ctx.get().setPacketHandled(true);
    }

}
