/*
 * Copyright (c) 2015 by vincent.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.vincent.demo.app.view.support;

import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 *
 *
 * @author Vincent
 * @version 1.0 2017/05/20 15:47
 */
public class CDATAXppDriver extends XppDriver {

    protected static String PREFIX_CDATA = "<![CDATA[";
    protected static String SUFFIX_CDATA = "]]>";

    public CDATAXppDriver() {
        super(new XmlFriendlyNameCoder());
    }

    /**
     * @since 1.4
     */
    public CDATAXppDriver(NameCoder nameCoder) {
        super(nameCoder);
    }

    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out, getNameCoder()) {

            @Override
            protected void writeText(QuickWriter writer, String text) {
                writer.write(PREFIX_CDATA);
                writer.write(text);
                writer.write(SUFFIX_CDATA);
            }
        };
    }
}
