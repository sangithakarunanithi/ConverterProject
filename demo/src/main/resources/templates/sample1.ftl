<?xml version=”1.0” encoding=”UTF-8” standalone=”no”?>
<xsl:stylesheet version="1.0">
  <xsl:template match="/"/>
<#list nodeList as node>
<${node.canonicalMapping}>
        <#list childMaps as child>
            <${child.canonicalMapping}>
                <xsl:value-of select="${child.xpath}"/>
            </${child.canonicalMapping}>
        </#list>
</${node.canonicalMapping}>
</#list>
  </xsl:template>
</xsl:stylesheet>
