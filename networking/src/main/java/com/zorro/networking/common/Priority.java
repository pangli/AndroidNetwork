package com.zorro.networking.common;

/**
 * Package:   com.zorro.networking.common
 * ClassName: Priority
 * Created by Zorro on 2020/5/6 19:20
 * 备注： 优先级
 */

/**
 * Priority levels recognized by the request server.
 */
public enum Priority {
    /**
     * NOTE: DO NOT CHANGE ORDERING OF THOSE CONSTANTS UNDER ANY CIRCUMSTANCES.
     * Doing so will make ordering incorrect.
     */

    /**
     * Lowest priority level. Used for prefetches of data.
     */
    LOW,

    /**
     * Medium priority level. Used for warming of data that might soon get visible.
     */
    MEDIUM,

    /**
     * Highest priority level. Used for data that are currently visible on screen.
     */
    HIGH,

    /**
     * Highest priority level. Used for data that are required instantly(mainly for emergency).
     */
    IMMEDIATE

}
