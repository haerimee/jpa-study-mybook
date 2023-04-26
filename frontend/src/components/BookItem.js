import React from "react";
import { useNavigate } from "react-router-dom";
import MyButton from "./MyButton";

const BookItem = ({ isbn, author, description, image, title, content, score, isReviewPage, id, createdDate }) => {
    const navigate = useNavigate();
    //
    const goDetail = () => {
        if(!isReviewPage){
            navigate(`/book/${isbn}`);
        }else{
            navigate(`/book/${id}`,{state: isReviewPage});
        }
    };

    return (
        <div className="BookItem">
            <div
                //onClick={goDetail}
                //className={["emotion_img_wrapper", `emotion_img_wrapper_${emotion}`].join(" ")}
                className="book_img_wrapper"
            >
                <img src={image} />
            </div>
            <div
                onClick={goDetail}
                className="info_wrapper"
            >
                <div className="diary_date">
                    {title}
                </div>
                <div>
                    {author}
                </div>
                <div className="diary_content_preview">
                    {!isReviewPage ? description : content}

                </div>

            </div>

            {
                isReviewPage &&
                <div className="btn_wrapper">
                    <MyButton text={'수정하기'} onClick={() => navigate(`/edit/${id}`)}  />
                    <p style={{fontSize: "13px", textAlign:"right"}}>{createdDate}</p>
                </div>
            }
        </div>
    )
};

export default React.memo(BookItem);