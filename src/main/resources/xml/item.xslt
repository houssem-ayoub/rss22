<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:rss="http://univrouen.fr/rss22" version="3.0">
    <xsl:output method="html" encoding="UTF8" indent="yes" version="5"/>
    <xsl:template match="rss:item">
        <xsl:text disable-output-escaping='yes'>&lt;!DOCTYPE html&gt;
        </xsl:text>
        <xsl:element name="html">
            <xsl:attribute name="lang">fr</xsl:attribute>
            <xsl:element name="head">
                <xsl:element name="title">
                    <xsl:text>Article - RSS22</xsl:text>
                </xsl:element>
                <xsl:element name="link">
                    <xsl:attribute name="rel">stylesheet</xsl:attribute>
                    <xsl:attribute name="type">text/css</xsl:attribute>
                    <xsl:attribute name="href">/css/item-style.css</xsl:attribute>
                </xsl:element>
            </xsl:element>
            <xsl:element name="body">
                <xsl:element name="nav">
                    <xsl:element name="div">
                        <xsl:attribute name="class">logo</xsl:attribute>
                        <xsl:element name="img">
                            <xsl:attribute name="class">logo</xsl:attribute>
                            <xsl:attribute name="src">/img/univ-logo.png</xsl:attribute>
                            <xsl:attribute name="alt">logo Université de Rouen Normandie</xsl:attribute>
                        </xsl:element>
                    </xsl:element>
                    <xsl:element name="a">
                        <xsl:attribute name="href">/</xsl:attribute>
                        <xsl:text>Accueil</xsl:text>
                    </xsl:element>
                    <xsl:element name="a">
                        <xsl:attribute name="href">/rss22/resume/html</xsl:attribute>
                        <xsl:text>Liste des articles</xsl:text>
                    </xsl:element>
                    <xsl:element name="a">
                        <xsl:attribute name="href">/help</xsl:attribute>
                        <xsl:text>Aide</xsl:text>
                    </xsl:element>
                </xsl:element>
                <xsl:element name="main">
                    <xsl:element name="h1">
                        <xsl:value-of select="rss:title"/>
                    </xsl:element>
                    <xsl:element name="div">
                        <xsl:attribute name="class">categories</xsl:attribute>
                        <xsl:text>Catégories: </xsl:text>
                        <xsl:for-each select="rss:category">
                            <xsl:element name="i">
                                <xsl:value-of select="./@term"/>
                            </xsl:element>
                        </xsl:for-each>
                    </xsl:element>
                    <xsl:element name="p">
                        <xsl:attribute name="class">date</xsl:attribute>
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
                    <xsl:element name="div">
                        <xsl:attribute name="class">center-content</xsl:attribute>
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
                <xsl:if test="rss:author|rss:contributor">
                    <xsl:element name="ul">
                        <xsl:apply-templates select="rss:author"/>
                        <xsl:apply-templates select="rss:contributor"/>
                    </xsl:element>
                </xsl:if>
            </xsl:element>
        </xsl:element>
    </xsl:template>
    <xsl:template match="rss:author">
        <xsl:element name="li">
            <xsl:value-of select="concat('Auteur: ',rss:name)"/>
            <xsl:if test="rss:email|rss:uri">
                <xsl:text>(</xsl:text>
                <xsl:if test="rss:email">
                    <xsl:value-of select="concat('email: ',rss:email)"/>
                    <xsl:if test="rss:uri"><xsl:text>/ </xsl:text></xsl:if>
                </xsl:if>
                <xsl:if test="rss:uri">
                    <xsl:text>uri: </xsl:text>
                    <xsl:element name="a">
                        <xsl:attribute name="href"><xsl:value-of select="rss:uri/@href"/></xsl:attribute>
                        <xsl:value-of select="rss:uri/@href"/>
                    </xsl:element>
                </xsl:if>
                <xsl:text>)</xsl:text>
            </xsl:if>
        </xsl:element>
    </xsl:template>
    <xsl:template match="rss:contributor">
        <xsl:element name="li">
            <xsl:value-of select="concat('Contributeur: ',rss:name)"/>
            <xsl:if test="rss:email|rss:uri">
                <xsl:text>(</xsl:text>
                <xsl:if test="rss:email">
                    <xsl:value-of select="concat('email: ',rss:email)"/>
                    <xsl:if test="rss:uri"><xsl:text>/</xsl:text></xsl:if>
                </xsl:if>
                <xsl:if test="rss:uri">
                    <xsl:text>uri: </xsl:text>
                    <xsl:element name="a">
                        <xsl:attribute name="href"><xsl:value-of select="rss:uri/@href"/></xsl:attribute>
                        <xsl:value-of select="rss:uri/@href"/>
                    </xsl:element>
                </xsl:if>
                <xsl:text>)</xsl:text>
            </xsl:if>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>