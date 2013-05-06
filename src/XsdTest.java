/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.sun.xml.internal.xsom.*;
import com.sun.xml.internal.xsom.XSParticle;
import com.sun.xml.internal.xsom.XSTerm;
import com.sun.xml.internal.xsom.parser.XSOMParser;
import org.xml.sax.SAXException;

import java.util.Iterator;

public class XsdTest {
    private final static String t = "   ";
    private final static String z = ", ";
    private final static String tt = t + t;


    public static void doXSParticle(XSParticle particle) {
        if (particle == null) {
            return;
        }
        XSTerm term = particle.getTerm();
        if (term.isModelGroup()) {
            XSModelGroup modelGroup = term.asModelGroup();
            for (XSParticle child : modelGroup.getChildren()) {
                if (child.getTerm().isElementDecl()) {
                    System.out.println(t + child.getTerm().asElementDecl().getName());
                }
                if (child.getTerm().isModelGroupDecl()) {
                    System.out.println(t + child.getTerm().asModelGroupDecl().getName());
                }
            }
        }

    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SAXException {
        XSOMParser parser = new XSOMParser();
        parser.parse("src/grammar/html_5.xsd");
        XSSchemaSet schemaSet = parser.getResult();
        XSSchema schema = schemaSet.getSchema("html-5");

        for (Iterator<XSElementDecl> iterator = schema.iterateElementDecls(); iterator.hasNext(); ) {
            XSElementDecl elementDecl = iterator.next();
            System.out.println(elementDecl.getName());
            if (elementDecl.getType().isComplexType()) {
                doXSParticle(elementDecl.getType().asComplexType().getContentType().asParticle());
            }
        }
    }
}
