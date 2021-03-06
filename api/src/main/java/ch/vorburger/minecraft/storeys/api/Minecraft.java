/*
 * ch.vorburger.minecraft.storeys
 *
 * Copyright (C) 2016 - 2018 Michael Vorburger.ch <mike@vorburger.ch>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package ch.vorburger.minecraft.storeys.api;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ServiceProxyBuilder;

@VertxGen
@ProxyGen
public interface Minecraft {

    void showTitle(String playerUUID, String message, Handler<AsyncResult<Void>> handler);

    void narrate(String playerUUID, String entity, String text, Handler<AsyncResult<Void>> handler);

    /**
     * Runs a Minecraft command.
     * This does not register a new command, but runs one.
     * @param command one single command without the starting slash
     */
    void runCommand(String playerUUID, String command, Handler<AsyncResult<Void>> handler);

    void getItemHeld(String playerUUID, HandType hand, Handler<AsyncResult<ItemType>> handler);

    void addRemoveItem(String playerUUID, int amount, ItemType item, Handler<AsyncResult<Void>> handler);

    void whenInside(String playerUUID, String name, Handler<AsyncResult<Void>> handler);

    /**
     * The service address.
     */
    String ADDRESS = "ch.vorburger.minecraft.storeys";

    /**
     * Method called to create a proxy to consume the service.
     *
     * @param vertx Vert.x
     * @return the proxy
     */
    static Minecraft createProxy(Vertx vertx) {
        return new ServiceProxyBuilder(vertx).setAddress(ADDRESS).build(Minecraft.class);
    }
}
