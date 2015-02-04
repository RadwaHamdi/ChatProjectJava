<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : ChatXSLT.xsl
    Created on : January 31, 2015, 5:47 PM
    Author     : Radwa
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <html>
  <body>
  <h2>My CD Collection</h2>
    <table border="1">
      <tr bgcolor="#9acd32">
        <td>
            <xsl:value-of select="chat/header"/>
        </td>
      </tr>
      <tr>
      <xsl:for-each select="chat/member">
      
          
        <td><xsl:value-of select="."/></td>
        
      
      </xsl:for-each>
      </tr>
      <xsl:for-each select="chat/message">
      <tr>
          
          <td>
              <xsl:value-of select="sender"/>
          </td>
          <td>
              <xsl:value-of select="body"/>
          </td>
      </tr>
      </xsl:for-each>
    </table>
  </body>
  </html>
</xsl:template>
</xsl:stylesheet>

