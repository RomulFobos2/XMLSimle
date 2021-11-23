<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <xsl:element name="articles">
            <xsl:for-each select="articles/article">
                <xsl:element name="article">
					<xsl:element name="id_art" >
                        <xsl:value-of select="@id_art"/>
                    </xsl:element>	
					<xsl:element name="name" >
                        <xsl:value-of select="@name"/>
                    </xsl:element>
					<xsl:element name="code" >
                        <xsl:value-of select="@code"/>
                    </xsl:element>
					<xsl:element name="username" >
                        <xsl:value-of select="@username"/>
                    </xsl:element>					
					<xsl:element name="guid" >
                        <xsl:value-of select="@guid"/>
                    </xsl:element>					
                </xsl:element>
            </xsl:for-each>
        </xsl:element>
    </xsl:template>
</xsl:stylesheet>
