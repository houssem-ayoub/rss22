<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rss="http://univrouen.fr/rss22" version="3.0">
    <xsl:output method="html" encoding="UTF8" indent="yes" version="5"/>
    <xsl:template match="rss:items">
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;
</xsl:text>
        <xsl:element name="html">
            <xsl:attribute name="lang">fr</xsl:attribute>
            <xsl:element name="head">
                <xsl:element name="title">
                    <xsl:text>Liste des Articles - RSS22</xsl:text>
                </xsl:element>
                <xsl:element name="link">
                    <xsl:attribute name="rel">stylesheet</xsl:attribute>
                    <xsl:attribute name="type">text/css</xsl:attribute>
                    <xsl:attribute name="href">style.css</xsl:attribute>
                </xsl:element>
            </xsl:element>
            <xsl:call-template name="body"/>
        </xsl:element>
    </xsl:template>
    <xsl:template name="body">
        <xsl:element name="body">
            <xsl:element name="h1">
                <xsl:text>List des articles</xsl:text>
            </xsl:element>
            <xsl:choose>
                <xsl:when test="rss:item">
                    <xsl:element name="table">
                        <xsl:element name="thead">
                            <xsl:element name="tr">
                                <xsl:element name="th">
                                    <xsl:text>Sommaire</xsl:text>
                                </xsl:element>
                            </xsl:element>
                        </xsl:element>
                        <xsl:element name="tbody">
                            <xsl:element name="tr">
                                <xsl:element name="th">
                                    <xsl:text>n°</xsl:text>
                                </xsl:element>
                                <xsl:element name="th">
                                    <xsl:text>GUID</xsl:text>
                                </xsl:element>
                                <xsl:element name="th">
                                    <xsl:text>TITRE</xsl:text>
                                </xsl:element>
                                <xsl:element name="th">
                                    <xsl:text>DATE</xsl:text>
                                </xsl:element>
                            </xsl:element>
                            <xsl:apply-templates select="rss:item"/>
                        </xsl:element>
                    </xsl:element>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:element name="p">
                        <xsl:text>Aucun article trouvé</xsl:text>
                    </xsl:element>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:element>
    </xsl:template>
    <xsl:template match="rss:item">
        <xsl:element name="tr">
            <xsl:element name="td">
                <xsl:value-of select="position()"/>
            </xsl:element>
            <xsl:element name="td">
                <xsl:value-of select="@guid"/>
            </xsl:element>
            <xsl:element name="td">
                <xsl:element name="a">
                    <xsl:attribute name="href">
                        <xsl:text disable-output-escaping="yes">/rss22/html/</xsl:text>
                        <xsl:value-of select="@guid"/>
                    </xsl:attribute>
                    <xsl:value-of select="substring(rss:title, 1, 45)"/>
                </xsl:element>
            </xsl:element>
            <xsl:element name="td">
                <xsl:text>publié le </xsl:text><xsl:value-of select="concat(
                        substring(rss:date,9,2),'-',
                        substring(rss:date,6,2),'-',
                        substring(rss:date,1,4),' ',
                        substring(rss:date,12,2),':',
                        substring(rss:date,15,2),':',
                        substring(rss:date,18,2)
                        )"/>
            </xsl:element>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>