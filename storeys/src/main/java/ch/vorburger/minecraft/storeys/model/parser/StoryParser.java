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
package ch.vorburger.minecraft.storeys.model.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.inject.Inject;

import ch.vorburger.minecraft.storeys.model.Action;
import ch.vorburger.minecraft.storeys.model.Story;

public class StoryParser {

    private final CommandMapping mapping;

    @Inject
    public StoryParser(CommandMapping mapping) {
        this.mapping = mapping;
    }

    public Story parse(String story) {
        List<Action<?>> actions = new ArrayList<>();
        while (!"".equals(story)) {
            for (CommandMapping.Mapping mapping : mapping.getMappings()) {
                Matcher matcher = mapping.getRegex().matcher(story);
                if (matcher.find()) {
                    Action<?> action = mapping.getActionProvider().get();
                    actions.add(action);
                    action.setParameter(matcher.group(1));
                    story = matcher.replaceFirst("").trim();
                    break;
                }
            }
        }
        return new Story(actions);
    }
}
