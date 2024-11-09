package me.mixces.ornithe_motionblur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.ornithemc.osl.entrypoints.api.ModInitializer;

public class OrnitheMotionBlur implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("Ornithe Motion Blur");

	@Override
	public void init() {
		LOGGER.info("Initializing Ornithe Motion Blur!");
	}
}
