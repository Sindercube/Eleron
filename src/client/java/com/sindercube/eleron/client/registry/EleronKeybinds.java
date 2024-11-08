package com.sindercube.eleron.client.registry;

import com.sindercube.eleron.client.util.InclusiveKeyBinding;
import com.sindercube.eleron.content.packet.ElytraClosePacket;
import com.sindercube.eleron.content.packet.SmokeStackBoostPacket;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class EleronKeybinds {

	public static void init() {
		ClientTickEvents.END_CLIENT_TICK.register(EleronKeybinds::handleSmokestackBoost);
	}

	public static final KeyBinding SMOKESTACK_BOOST = KeyBindingHelper.registerKeyBinding(InclusiveKeyBinding.create(
		"key.eleron.smokestack_boost",
		InputUtil.Type.KEYSYM,
		GLFW.GLFW_KEY_SPACE,
		"key.eleron.categories.movement"
	));
	public static final KeyBinding CLOSE_ELYTRA = KeyBindingHelper.registerKeyBinding(InclusiveKeyBinding.create(
		"key.eleron.close_elytra",
		InputUtil.Type.KEYSYM,
		GLFW.GLFW_KEY_LEFT_SHIFT,
		"key.eleron.categories.movement"
	));

	private static boolean WAS_KEYBIND_PRESSED = false;

	public static void handleSmokestackBoost(MinecraftClient client) {
		if (!WAS_KEYBIND_PRESSED && SMOKESTACK_BOOST.isPressed()) ClientPlayNetworking.send(SmokeStackBoostPacket.INSTANCE);
		WAS_KEYBIND_PRESSED = SMOKESTACK_BOOST.isPressed();

		if (CLOSE_ELYTRA.isPressed()) ClientPlayNetworking.send(ElytraClosePacket.INSTANCE);
	}


}
