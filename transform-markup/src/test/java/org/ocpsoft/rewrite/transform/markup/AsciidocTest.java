package org.ocpsoft.rewrite.transform.markup;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class AsciidocTest
{

   @Test
   public void shouldRenderBoldText()
   {

      String asciidoc = "This is **bold**!";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).contains("This is <strong>bold</strong>!");

   }

   @Test
   public void shouldRenderLiteral()
   {

      String asciidoc = "The +EventMetadata+ interface";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).contains("The <tt>EventMetadata</tt> interface");

   }

   @Test
   public void shouldRenderOneLineTitleLevel1()
   {

      String asciidoc = "" +
               "== Level1 ==\n\n" +
               "Some text";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).matches("(?s).*<h2[^>]*>Level1</h2>.*");

   }

   @Test
   public void shouldRenderTwoLineTitleLevel1()
   {

      String asciidoc = "" +
               "Level1\n" +
               "------\n\n" +
               "Some text";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).matches("(?s).*<h2[^>]*>Level1</h2>.*");

   }

   @Test
   public void shouldRenderOneLineTitleLevel2()
   {

      String asciidoc = "" +
               "== Level2 ==\n\n" +
               "Some text";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).matches("(?s).*<h2[^>]*>Level2</h2>.*");

   }

   @Test
   public void shouldRenderOneLineTitleLevel3()
   {

      String asciidoc = "" +
               "=== Level3 ===\n\n" +
               "Some text";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).matches("(?s).*<h3[^>]*>Level3</h3>.*");

   }

   @Test
   public void shouldRenderQuotes()
   {

      String asciidoc = "" +
               "[quote]\n" +
               "To be or not to be.";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).matches("(?s).*<blockquote>\\s*To be or not to be.\\s*</blockquote>.*");

   }

   @Test
   public void shouldRenderLists()
   {

      String asciidoc = "" +
               "- Item1\n" +
               "- Item2\n";
      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html)
               .contains("<ul>")
               .contains("</ul>")
               .matches("(?s).*<li>\\s*<p>\\s*Item1\\s*</p>\\s*</li>.*")
               .matches("(?s).*<li>\\s*<p>\\s*Item2\\s*</p>\\s*</li>.*");

   }

   @Test
   public void shouldRenderSourceCode()
   {

      String asciidoc = "\n" +
               "[source, java] \n" +
               "---- \n" +
               "public interface EventMetadata {    \n" +
               "    public Set<Annotation> getQualifiers();\n" +
               "    public InjectionPoint getInjectionPoint();\n" +
               "    public Type getResolvedType();\n" +
               " } \n" +
               "----";

      String html = Asciidoc.partialDocument().transform(asciidoc);

      assertThat(html).contains("<div class=\"listingblock\">\n" +
               "  \n" +
               "  <div class=\"content monospaced\">\n" +
               "    \n" +
               "    <pre class=\"highlight\"><code class=\"java\">public interface EventMetadata {\n" +
               "    public Set&lt;Annotation&gt; getQualifiers();\n" +
               "    public InjectionPoint getInjectionPoint();\n" +
               "    public Type getResolvedType();\n" +
               " }</code></pre>\n" +
               "    \n" +
               "  </div>\n" +
               "</div>\n" +
               "\n");
   }

}
