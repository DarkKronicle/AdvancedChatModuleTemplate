package io.github.darkkronicle.acmoduletemplate;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.interfaces.IInitializationHandler;
import io.github.darkkronicle.acmoduletemplate.config.ModuleConfigStorage;
import io.github.darkkronicle.advancedchatcore.chat.ChatHistory;
import io.github.darkkronicle.advancedchatcore.config.gui.GuiConfigHandler;
import io.github.darkkronicle.advancedchatcore.interfaces.IChatMessageProcessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModuleInitHandler implements IInitializationHandler {

    @Override
    public void registerModHandlers() {
        ConfigManager.getInstance()
                .registerConfigHandler(ACModuleTemplate.MOD_ID, new ModuleConfigStorage());
        GuiConfigHandler.getInstance()
                .addGuiSection(
                        GuiConfigHandler.createGuiConfigSection(
                                "acmoduletemplate.tab.general",
                                ModuleConfigStorage.General.OPTIONS));

        // Register on new message
        ChatHistory.getInstance()
                .addOnUpdate(
                        (message, type) -> {
                            if (type == IChatMessageProcessor.UpdateType.ADDED) {
                                System.out.println("Chat message happened!");
                            }
                        });
        // Register on the clear
        ChatHistory.getInstance()
                .addOnClear(
                        () ->
                                System.out.println(
                                        ModuleConfigStorage.General.STRING_STUFF.config
                                                .getStringValue()));
    }
}
