package com.vanilla.experience;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HelloMessage {
    private static final Logger LOGGER = LogManager.getLogger();

    public HelloMessage(){
        LOGGER.info("[VEX] Starting Vanilla Experience, thanks for downloading!");
    }
}
