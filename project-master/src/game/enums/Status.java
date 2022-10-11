package game.enums;

/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    /**
     * use this status to be considered hostile towards enemy (e.g., to be attacked by enemy)
     */
    HOSTILE_TO_ENEMY, //
    /**
     * use this status to tell that current instance has "grown".
     */
    TALL, //
    /**
     * this is a reset button, only can use once
     */
    RESET_BUTTON, //
    /**
     *reset those tree, coins if we press the reset button
     */
    RESET, //
    /**
     *let the tree stop spawning for once (the reset game turn)
     */
    STOP_SPAWN, //
    /**
     *for those high grounds (player needs to jump onto high ground not walk onto high ground)
     */
    NEED_TO_JUMP, //
    /**
     *if player consumes power star, it will be invincible
     */
    INVINCIBLE, //
    /**
     * to let enemy cannot step into the floor
     */
    FLOOR_LAVA, //
    /**
     *hurts actor for 3 turns
     */
    FIRE,//
    /**
     * bottle item
     */
    BOTTLE,
    /**
     * When Princess Peach is locked
     */
    LOCKED,//
    /**
     *For flying Koopa to ignore terrain
     */
    IGNORE_TERRAIN, //
    /**
     *Fire buff after consuming fire flower
     */
    FIRE_BUFF, //
    /**
     * Wrench weapon
     */
    WRENCH, //
    /**
     * power water buff
     */
    POWERWATER

}
