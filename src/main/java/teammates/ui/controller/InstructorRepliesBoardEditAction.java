package teammates.ui.controller;

import teammates.common.datatransfer.attributes.TopicAttributes;
import teammates.common.datatransfer.attributes.RepliesAttributes;
import teammates.common.util.Assumption;
import teammates.common.util.Const;
import teammates.common.util.Logger;
import teammates.ui.pagedata.InstructorRepliesBoardEditPageData;

/**
 * Action: edit reply when Instructor is active user.
 *
 */
public class InstructorRepliesBoardEditAction extends Action {

    /*
     * Variable declarations
     */
    private InstructorRepliesBoardEditPageData data;
    private TopicAttributes topic;
    private RepliesAttributes reply;

    @Override
    protected ActionResult execute() {
        //get parameters for url request and assert not null
        String topicId = getRequestParamValue(Const.ParamsNames.TOPIC_ID);
        Assumption.assertPostParamNotNull(Const.ParamsNames.TOPIC_ID, topicId);
        String replyId = getRequestParamValue(Const.ParamsNames.REPLY_ID);
        Assumption.assertPostParamNotNull(Const.ParamsNames.REPLY_ID,  replyId);

        //set studentProfile as current user
        account.studentProfile = logic.getStudentProfile(account.googleId);

        //retrieve relevant topic from database
        topic = logic.getTopic(topicId);
        
        //retrieve relevant reply from topic
        reply = getReplyWithId(topic.getReplies(), Integer.parseInt(replyId));
        
        //set data object with relevant values
        data = new InstructorRepliesBoardEditPageData(account, sessionToken, topic, reply);

        return createShowPageResult(Const.ViewURIs.INSTRUCTOR_REPLIES_EDIT_PAGE, data);
    }

}
