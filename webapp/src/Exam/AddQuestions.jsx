import React from 'react';

const AddQuestions = (props) => {
<<<<<<< HEAD
return (
                    props.Questions.map((val, id) => {
                let addQuestionId = "addQuestion-${id}"
                return (
                    <div key={id}>
                        <label htmlFor={addQuestionId}> </label> 
                        <label htmlFor="Question">Question {id + 2} </label>
                <input type="text" required name={"QC" + (id + 2)} className="question-content"/> <br />
                <label>Please enter the correct answer</label>
                <input type="text"  required name= {"A" + (id + 2) + "a"} className="answer" placeholder="Answer..." /> <br />
                <label>Please enter your incorrect answers</label> <br />
                <input type="text" required name= {"A" + (id + 2) + "b"} className="answer" placeholder="Answer..." /> <br />
                <input type="text" name= {"A" + (id + 2) + "b"} className="answer" placeholder="Answer..." /> <br />
                <input type="text" name= {"A" + (id + 2) + "b"} className="answer" placeholder="Answer..." /> <br />
=======
    return (
        props.Questions.map((val, id) => {
            let addQuestionId = "addQuestion-${id}"
            return (
                <div key={id}>
                    <label htmlFor={addQuestionId}> </label>
                    <label htmlFor="Question">Question {id + 2} </label>
                    <input type="text" required name={"QC" + (id + 2)} className="question-content"/> <br/>
                    <label>Please enter the correct answer</label>
                    <input type="text" required name={"A" + (id + 2) + "a"} className="answer" placeholder="Answer..."/>
                    <br/>
                    <label>Please enter your incorrect answers</label> <br/>
                    <input type="text" required name={"A" + (id + 2) + "b"} className="answer" placeholder="Answer..."/>
                    <br/>
                    <input type="text" name={"A" + (id + 2) + "c"} className="answer" placeholder="Answer..."/> <br/>
                    <input type="text" name={"A" + (id + 2) + "d"} className="answer" placeholder="Answer..."/> <br/>
>>>>>>> branch 'master' of https://github.com/LMSGroupProject/group-commit-to-master.git
                </div>
            )
        })

    );
}
export default AddQuestions;
