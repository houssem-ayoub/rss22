<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rss="http://univrouen.fr/rss22" version="3.0">
    <xsl:output method="html" encoding="UTF8" indent="yes" version="5"/>
    <xsl:template match="rss:item">
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;</xsl:text>
        <xsl:element name="html">
            <xsl:attribute name="lang">fr</xsl:attribute>
            <xsl:element name="head">
                <xsl:element name="title">
                    <xsl:text>Article - RSS22</xsl:text>
                </xsl:element>
                <xsl:element name="link">
                    <xsl:attribute name="rel">stylesheet</xsl:attribute>
                    <xsl:attribute name="type">text/css</xsl:attribute>
                    <xsl:attribute name="href">style.css</xsl:attribute>
                </xsl:element>
            </xsl:element>
            <xsl:element name="body">
                <xsl:element name="div">
                    <xsl:text>Catégories: </xsl:text>
                    <xsl:for-each select="rss:category">
                        <xsl:element name="i">
                            <xsl:value-of select="./@term"/>
                        </xsl:element>
                    </xsl:for-each>
                </xsl:element>
                <xsl:element name="h1">
                    <xsl:value-of select="rss:title"/>
                </xsl:element>
                <xsl:element name="p">
                    <xsl:choose>
                        <xsl:when test="rss:published">
                            <xsl:text>publié le </xsl:text><xsl:value-of select="concat(
                        substring(rss:published,9,2),'-',
                        substring(rss:published,6,2),'-',
                        substring(rss:published,1,4),' ',
                        substring(rss:published,12,2),':',
                        substring(rss:published,15,2),':',
                        substring(rss:published,18,2)
                        )"/>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:text>mise à jour le </xsl:text><xsl:value-of select="concat(
                        substring(rss:updated,9,2),'-',
                        substring(rss:updated,6,2),'-',
                        substring(rss:updated,1,4),' ',
                        substring(rss:updated,12,2),':',
                        substring(rss:updated,15,2),':',
                        substring(rss:updated,18,2)
                        )"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:element>
                <xsl:if test="rss:image">
                    <xsl:element name="img">
                        <xsl:attribute name="src">
                            <xsl:value-of select="rss:image/@href"/>
                        </xsl:attribute>
                        <xsl:attribute name="alt">
                            <xsl:value-of select="rss:image/@alt"/>
                        </xsl:attribute>
                    </xsl:element>
                    <xsl:element name="br"/>
                </xsl:if>
                <xsl:choose>
                    <xsl:when test="rss:content/@type='src'">
                        <xsl:element name="a">
                            <xsl:attribute name="href">
                                <xsl:value-of select="rss:content/@href"/>
                            </xsl:attribute>
                            <xsl:value-of select="rss:content/@href"/>
                        </xsl:element>
                    </xsl:when>
                    <xsl:otherwise>
                        <xsl:element name="p">
                            <xsl:value-of select="rss:content"/>
                        </xsl:element>
                    </xsl:otherwise>
                </xsl:choose>

            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>