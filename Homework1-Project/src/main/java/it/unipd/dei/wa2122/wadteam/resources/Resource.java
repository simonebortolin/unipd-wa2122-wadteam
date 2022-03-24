package it.unipd.dei.wa2122.wadteam.resources;

/*
 * Copyright 2018 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.json.JSONObject;

import java.io.*;

/**
 * Represents a generic resource.
 *
 * @author Nicola Ferro (ferro@dei.unipd.it)
 * @version HW1.00
 * @since 1.00
 */
public abstract class Resource {

     /**
     * Returns a JSON representation of the {@code Resource} into the given {@code OutputStream}.
     */
    public abstract JSONObject toJSON();
}

