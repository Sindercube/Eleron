package com.sindercube.eleron.content.packet;

import com.sindercube.eleron.Eleron;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public class SmokeStackDashPacket implements CustomPayload {

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static final SmokeStackDashPacket INSTANCE = new SmokeStackDashPacket();
    public static final CustomPayload.Id<SmokeStackDashPacket> ID = new CustomPayload.Id<>(Eleron.of("smokestack_launch"));
    public static final PacketCodec<RegistryByteBuf, SmokeStackDashPacket> PACKET_CODEC = PacketCodec.unit(INSTANCE);


    private static final int BOOST_TICKS = 40;
    private static final int COOLDOWN = 60;

    public void dash(ServerPlayNetworking.Context context) {
        PlayerEntity player = context.player();
        if (!player.isFallFlying()) return;
        if (player.getSmokeStackDashCooldown() > 0) return;
        if (player.getSmokeStacks() <= 0) return;

        player.removeSmokeStack();
        player.setFlightBoostTicks(BOOST_TICKS);
        player.setSmokeStackDashCooldown(COOLDOWN);
    }

}
