/*
 ** File: WebResponse.java
 **
 ** Date Created: December 2016
 **
 ** Copyright @ 2016-2018 Roberto Rodriguez.
 ** Email: robertoSoftwareEngineer@gmail.com
 **
 ** All rights reserved. No part of this software may be 
 ** reproduced, transmitted, transcribed, stored in a retrieval 
 ** system, or translated into any language or computer language, 
 ** in any form or by any means, electronic, mechanical, magnetic, 
 ** optical, chemical, manual or otherwise, without the prior 
 ** written permission of Roberto Rodriguez.
 **
 */
package com.system.enums;

public enum ROLE {
    SUPER_ADMIN(1),
    ADMIN(2),
    COOLLABORATOR(3);

    private Integer id;

    private ROLE(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

}
